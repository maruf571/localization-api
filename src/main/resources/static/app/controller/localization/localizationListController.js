app.controller("localizationListController",
    function($rootScope, $scope, $http, $location, $timeout , localizationService, languageService, ModalService) {

    var languageId = getParameterByName("languageId");

    function init(){
        localizationService.findAll("?languageId="+languageId)
        .then(function(resp){
            $scope.localizations = resp;
        });
    }

    $scope.showAModal = function(localization) {

            ModalService.showModal({
              templateUrl: "/html/localization/localization-single.html",
              controller: "localizationSingleController",
              inputs: {localization: localization, languageId:languageId}
            })
            .then(function(modal) {

              modal.element.modal();
              modal.close.then(function(result) {
                    if(localization.id){
                        localization = result;
                        //var index = $scope.localizations.index(localization);

                    }else{
                        $scope.localizations.push(result);
                    }
              });
            });

          };


    init();
});