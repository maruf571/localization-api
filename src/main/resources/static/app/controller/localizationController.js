app.controller("localizationController",
    function($rootScope, $scope, $http, $location, $timeout , localizationService) {

    function init(){
    }

    $scope.save = function(language){
        localizationService.save(language)
        .then(function(resp){
            console.log(resp);
         })
    }
    init();
});