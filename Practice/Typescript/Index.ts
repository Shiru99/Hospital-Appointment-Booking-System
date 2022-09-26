export{}

var message="Hello World";

console.log(message);


// variable declaration : let vs const
let sum = 90;
sum = 100;

const title = "Typescript";

// variable types

let isBeginner: boolean = true;
let total: number = 0;
let person: string = "John Doe";

let paragrabh: string = `My name is ${person}
I am a beginner in Typescript`;

console.log(paragrabh);

let n: null = null;
let u: undefined = undefined;


// array
let list1: number[] = [1, 2, 3];
let list2: Array<number> = [1, 2, 3];

// tuple
let person1: [string, number] = ["John Doe", 22];

// enum
enum Color {Red, Green, Blue};
let c: Color = Color.Red;
console.log(c);

// any
let randomValue: any = 10;
randomValue = true;
randomValue = "John Doe";

// unknown
let myVariable: unknown =  "John Doe";
// console.log(myVariable.length);  // error

let strLength: number = (myVariable as string).length;  // type assertion


let multiType: number | boolean;
multiType = 20;
multiType = true;


// function - ? for optional parameter, = for default parameter

function add(num1: number, num2?: number): number {
    if (num2)
        return num1 + num2;
    else
        return num1;
}

function substract(num1: number, num2: number=0): number {
    if (num2)
        return num1 - num2;
    else
        return num1;
}

console.log(add(5, 10));
console.log(add(5));
console.log(substract(5, 10));
console.log(substract(5));

