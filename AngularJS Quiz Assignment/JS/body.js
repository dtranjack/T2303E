var modular = angular.module("my-app", []);
modular.controller("QuizController", function ($scope) {
    $scope.questions = [
        {
            text: "Việt Nam có bao nhiêu sân bay quốc tế",
            options: ["22", "15", "30", "10"],
            correctAnswer: "10",
            answered: false,
        },
        {
            text: "Tỉnh nào duy nhất tại Việt Nam có hai sân bay dân sự?",
            options: ["Kiên Giang", "Hà Nội", "Hải Phòng", "Nha Trang"],
            correctAnswer: "Kiên Giang",
            answered: false,
        },
        {
            text: "Ven biển miền Trung có bao nhiêu sân bay?",
            options: ["18", "10", "9", "4"],
            correctAnswer: "9",
            answered: false,
        },
        {
            text: "Sân bay nào đông đúc nhất Việt Nam?",
            options: ["Thanh Hóa", "Tân Sơn Nhất", "Nội Bài", "Đà Nẵng"],
            correctAnswer: "Tân Sơn Nhất",
            answered: false,
        },
    ];

    $scope.currentQuestionIndex = 0;

    $scope.checkAnswer = function (selectedAnswer) {
        selectedAnswer.answered = true;
    };

    $scope.nextQuestion = function () {
        if ($scope.currentQuestionIndex < $scope.questions.length - 1) {
            $scope.currentQuestionIndex++;
        }
    };

    $scope.previousQuestion = function () {
        if ($scope.currentQuestionIndex > 0) {
            $scope.currentQuestionIndex--;
        }
    };

    $scope.resetQuiz = function () {
        $scope.currentQuestionIndex = 0;
    }

    $scope.fullReset = function () {
        location.reload()
    }
});

