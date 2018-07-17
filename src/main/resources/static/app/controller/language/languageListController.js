app.controller("languageListController",
    function($rootScope, $scope, $http, $location, $timeout , languageService, ModalService) {

    var projectId = getParameterByName("projectId");
    $scope.projectId = projectId;

    function init(){

        languageService.findAll('?projectId='+projectId)
        .then(function(resp){
            $scope.languages = resp.content;
        })
    }

    $scope.showAModal = function(language) {

        ModalService.showModal({
          templateUrl: "/html/language/language-single.html",
          controller: "languageSingleController",
          inputs: {language: language, projectId: projectId}
        })
        .then(function(modal) {

          modal.element.modal();
          modal.close.then(function(result) {
               $scope.languages.push(result);
          });
        });

      };

    init();
});