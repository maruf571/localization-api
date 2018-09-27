app.controller("localizationSingleController",
    function($scope, $http, $location, $element, close, localizationService, languageService, localizationKey, languageId) {

    function init(){

        $scope.localizationKey = {};
        if(localizationKey){
            $scope.localizationKey = localizationKey;
        }
    }

    $scope.save = function(localizationKey){

        localizationKey.languageId = languageId;

        localizationService.submit(localizationKey)
        .then(function(resp){
            console.log(resp);
            $element.modal('hide');
            close(resp, 500);
         })
    }

    init();
});