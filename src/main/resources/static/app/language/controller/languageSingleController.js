/**
* We injected 'language' from languageListController
*/
app.controller("languageSingleController",
    function($scope, $element, close, language, projectId, languageService) {

      function init(){

          $scope.language = {};
          if(language){
              $scope.language = language;
          }
      }

    $scope.save = function(language){

        $scope.language.project = {id: projectId};

        languageService.submit(language)
        .then(function(resp){
            console.log(resp);
            $element.modal('hide');
             close(resp, 500);
         })
    }

    init();
});