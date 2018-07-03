app.controller("localizationListController",
    function($rootScope, $scope, $http, $location, $timeout , localizationService, languageService, ModalService) {

    function init(){
        localizationService.findAll()
        .then(function(resp){
            $scope.localizations = resp.content;
        });
    }

    $scope.showAModal = function(localization) {

            ModalService.showModal({
              templateUrl: "/html/localization/localization-single.html",
              controller: "localizationSingleController",
              inputs: {localization: localization}
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