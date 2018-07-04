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

              modal.element.modal();
              modal.close.then(function(result) {

              });
            });

          };


    init();
});