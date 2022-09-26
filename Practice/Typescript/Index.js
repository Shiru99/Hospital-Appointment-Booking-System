"use strict";
exports.__esModule = true;
var message = "Hello World";
console.log(message);
// variable declaration : let vs const
var sum = 90;
sum = 100;
var title = "Typescript";
// variable types
var isBeginner = true;
var total = 0;
var person = "John Doe";
var paragrabh = "My name is ".concat(person, "\nI am a beginner in Typescript");
console.log(paragrabh);
var n = null;
var u = undefined;
// array
var list1 = [1, 2, 3];
var list2 = [1, 2, 3];
// tuple
var person1 = ["John Doe", 22];
// enum
var Color;
(function (Color) {
    Color[Color["Red"] = 0] = "Red";
    Color[Color["Green"] = 1] = "Green";
    Color[Color["Blue"] = 2] = "Blue";
})(Color || (Color = {}));
;
var c = Color.Red;
console.log(c);
// any
var randomValue = 10;
randomValue = true;
randomValue = "John Doe";
// unknown
var myVariable = "John Doe";
// console.log(myVariable.length);  // error
var strLength = myVariable.length; // type assertion
var multiType;
multiType = 20;
multiType = true;
// function - ? for optional parameter, = for default parameter
function add(num1, num2) {
    if (num2)
        return num1 + num2;
    else
        return num1;
}
function substract(num1, num2) {
    if (num2 === void 0) { num2 = 0; }
    if (num2)
        return num1 - num2;
    else
        return num1;
}
console.log(add(5, 10));
console.log(add(5));
console.log(substract(5, 10));
console.log(substract(5));
