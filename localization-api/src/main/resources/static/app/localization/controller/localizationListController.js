app.controller("localizationListController",
    function($rootScope, $scope, $q, $httpParamSerializer, $http, $location, $timeout , localizationService, languageService, ModalService) {

    var languageId = getParameterByName("languageId");
    var projectId = getParameterByName("projectId")
    $scope.projectId = projectId;

    function init(){

        languageService.findOne(languageId).then(function(resp){
            $scope.language = resp;
        })

        //var qs = $httpParamSerializer({projectId: projectId, languageId: languageId});

        localizationService.findAll("project/"+projectId+"/language/"+languageId)
        .then(function(resp){
            $scope.localizations = resp;
        });
    }

    $scope.showAModal = function(localizationKey) {

            ModalService.showModal({
              templateUrl: "/app/localization/localization-single.html",
              controller: "localizationSingleController",
              inputs: {localizationKey: localizationKey, languageId:languageId}
            })
            .then(function(modal) {
                  modal.element.modal();
                  modal.close.then(function(result) {
                        if(localizationKey){
                            localizationKey = result;
                        }else{
                            $scope.localizations.push(result);
                        }
                  });
            });
    };


    $scope.confirmCallbackMethod = function(localization){
        localizationService.delete(localization.id)
        .then(function(resp){
            var index =  $scope.localizations.indexOf(localization)
            $scope.localizations.splice(index, 1);
        })
    }

    $scope.goLanguage = function(){
        $location.path("language/language-list")
            .search({projectId: projectId});
    }

    $scope.export = function(projectId, languageId){
        console.log("projectId: " + projectId + " languageId: " + languageId);
    }

    $scope.export = function(projectId, languageId){
        console.log("projectId: " + projectId + " languageId: " + languageId);

        var self = this;
        var deferred = $q.defer();
        var defaultFileName = $scope.language.name +"_localization.xlsx";
        $http.get("/api/localizations/project/"+projectId+"/language/"+languageId+"/export", { responseType: "blob" })
        .then(function (data) {
          saveAs(data.data, defaultFileName)
          deferred.resolve(defaultFileName);
        },
        function (data) {
           var e = "download fail";
           deferred.reject(e);
        });
        return deferred.promise;
    }


    $scope.fileAutoUpload = function(element){
            console.log("projectId: " + projectId + " languageId: " + languageId);
            if(element.files.length == 0){
                return;
            }

            var fd = new FormData();
            fd.append('file', element.files[0]);
            $http.post("/api/localizations/project/"+projectId+"/language/"+languageId+"/import", fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(function(resp){
                console.log("File upload successfull");
            });
        }

    init();
});