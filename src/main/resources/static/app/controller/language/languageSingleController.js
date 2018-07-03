/**
* We injected 'language' from languageListController
*/
app.controller("languageSingleController",
    function($scope, $element, close, language, languageService) {

      function init(){

          $scope.language = {};
          if(language){
              $scope.language = brand;
          }
      }

    $scope.save = function(language){
        languageService.save(language)
        .then(function(resp){
            console.log(resp);
            close(resp, 500);
            $element.modal('hide');
         })
    }

    init();
});