app.controller("languageListController",
    function($rootScope, $scope, $http, $location, $timeout , languageService, ModalService) {

    var projectId = getParameterByName("projectId");
    $scope.projectId = projectId;

    function init(){

        languageService.findAll('?projectId='+projectId)
        .then(function(resp){
            $scope.languages = resp;
        })
    }

    $scope.showAModal = function(language) {

        ModalService.showModal({
          templateUrl: "/app/language/language-single.html",
          controller: "languageSingleController",
          inputs: {language: language, projectId: projectId}
        })
        .then(function(modal) {

          modal.element.modal();
          modal.close.then(function(result) {
                if(language){
                    language = result;
                }
                else{
                    $scope.languages.push(result);
               }
          });
        });

    };

    $scope.goLocalization = function(language){
        $location.path("/localization/localization-list")
            .search({projectId: projectId, languageId:language.id})
    }

    init();
});