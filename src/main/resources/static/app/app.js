var app = angular.module("localizationApp", [
 'ui.router',
 'angularModalService'
]);

app.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');
    $stateProvider
        .state('home', {
            url: '/',
            views:{
                'content@':{
                    templateUrl: '/html/home.html'
                }
            }
        })
        .state('dynamicState1', {
            url: '/:part1',
            views:{
                'content@':{
                    templateUrl: function(urlAttr){
                        return  "/html/" + urlAttr.part1 + ".html";
                    }
                }
            }
        })
        .state('dynamicState2', {
            url: '/:part1/:part2',
            views:{
                'content@':{
                    templateUrl: function(urlAttr){
                        return "/html/" + urlAttr.part1 + "/" + urlAttr.part2 + ".html";
                    }
                }
            }
        })
        .state('dynamicState3', {
            url: '/:part1/:part2/:part3',
            views:{
                'content@':{
                    templateUrl: function(urlAttr){
                        return "/html/" + urlAttr.part1 + "/" + urlAttr.part2 + urlAttr.part3 + ".html";
                    }
                }
            }
        })
});
