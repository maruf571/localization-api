app.controller("localizationSingleController",
    function($scope, $http, $location, $element, close, localizationService, languageService, localization, languageId) {

    function init(){

        $scope.localization = {};
        if(localization){
            $scope.localization = localization;
        }
    }

    $scope.save = function(localization){

        localization.languageId = languageId;

        localizationService.submit(localization)
        .then(function(resp){
            console.log(resp);
            $element.modal('hide');
            close(resp, 500);
         })
    }

    init();
});