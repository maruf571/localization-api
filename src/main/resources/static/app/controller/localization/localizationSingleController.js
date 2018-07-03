app.controller("localizationSingleController",
    function($scope, $http, $location, $element, localizationService, languageService, localization) {

    function init(){

        $scope.localization = {};
        if(localization){
            $scope.localization = localization;
        }

        languageService.findAll()
        .then(function(resp){
            $scope.languages = resp.content;
        });
    }

    $scope.save = function(localization){
        localizationService.save(localization)
        .then(function(resp){
            console.log(resp);
            close(resp, 500);
            $element.modal('hide');
         })
    }

    init();
});