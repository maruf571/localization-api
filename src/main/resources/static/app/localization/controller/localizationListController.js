app.controller("localizationListController",
    function($rootScope, $scope, $httpParamSerializer, $http, $location, $timeout , localizationService, languageService, ModalService) {

    var languageId = getParameterByName("languageId");
    var projectId = getParameterByName("projectId")

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
                        if(localization){
                            localization = result;

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
    init();
});