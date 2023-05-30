var app = angular.module('my-app', []);

app.controller('MyController', function($scope) {
    $scope.cards = [
        {
            title: 'ASP.NET MVC',
            author: 'Nick Harrison',
            description: 'Lorem ipsum',
            category: 'Software',
            img: '../img/aspnetmvc.jpeg'
        },
        {
            title: 'Node.js',
            author: 'Ryan Dahl',
            description: 'Lorem ipsum',
            category: 'Software',
            img: '../img/node.js-logo-image.png'
        },
        {
            title: 'React.js',
            author: 'Jordan Walke',
            description: 'Lorem ipsum',
            category: 'Framework',
            img: '../img/download.png'
        },
        {
            title: 'Typescript',
            author: 'Anders Hejlsberg',
            description: 'Lorem ipsum',
            category: 'Language',
            img: '../img/ts_logo.png'
        },
        {
            title: 'Sublime Text',
            author: 'Nick Harrison',
            description: 'Lorem ipsum',
            category: 'Software'
        },
        {
            title: 'Javascript',
            author: 'Nick Harrison',
            description: 'Lorem ipsum',
            category: 'Language'
        },
        {
            title: 'Laravel',
            author: 'Nick Harrison',
            description: 'Lorem ipsum',
            category: 'Framework'
        },
        {
            title: 'Python',
            author: 'Nick Harrison',
            description: 'Lorem ipsum',
            category: 'Language'
        }
    ];
});