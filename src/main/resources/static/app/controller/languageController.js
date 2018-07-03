app.controller("languageController",
    function($rootScope, $scope, $http, $location, $timeout , languageService) {

    function init(){
    }

    $scope.save = function(language){
        languageService.save(language)
        .then(function(resp){
            console.log(resp);
         })
    }
    init();
});