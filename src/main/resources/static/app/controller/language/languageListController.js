app.controller("languageListController",
    function($rootScope, $scope, $http, $location, $timeout , languageService, ModalService) {

    function init(){

        languageService.findAll()
        .then(function(resp){
            $scope.languages = resp.content;
        })
    }

    $scope.showAModal = function(language) {

        ModalService.showModal({
          templateUrl: "/html/language/language-single.html",
          controller: "languageSingleController",
          inputs: {language: language}
        })
        .then(function(modal) {
          // The modal object has the element built, if this is a bootstrap modal
          // you can call 'modal' to show it, if it's a custom modal just show or hide
          // it as you need to.
          modal.element.modal();
          modal.close.then(function(result) {
            $scope.message = result ? "You said Yes" : "You said No";
          });
        });

      };

    init();
});