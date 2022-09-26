"use strict";
var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
exports.__esModule = true;
function personDetail(name, age, address, phone) {
    console.log("\n    Person Details:\n                Name: ".concat(name, "\n                Age: ").concat(age, "\n                Address: ").concat(address, "\n                Phone: ").concat(phone));
}
personDetail("John Doe", 22, "New York", 1234567890);
function personDetails(person) {
    console.log("\n    Person Details:\n                Name: ".concat(person.name, "\n                Age: ").concat(person.age, "\n                Address: ").concat(person.address, "\n                Phone: ").concat(person.phone));
}
var person1 = {
    name: "John Doe",
    age: 22,
    address: "New York",
    phone: 1234567890
};
personDetails(person1);
// Class
var PersonClass = /** @class */ (function () {
    function PersonClass(name, age, address, phone) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
    }
    PersonClass.prototype.personDetails = function () {
        console.log("\n        Person Details:\n                    Name: ".concat(this.name, "\n                    Age: ").concat(this.age, "\n                    Address: ").concat(this.address, "\n                    Phone: ").concat(this.phone));
    };
    PersonClass.prototype.greet = function () {
        console.log("Hello ".concat(this.name));
    };
    return PersonClass;
}());
var person2 = new PersonClass("John Doe", 22, "New York", 1234567890);
person2.personDetails();
// Inheritance
var Employee = /** @class */ (function (_super) {
    __extends(Employee, _super);
    function Employee(name, age, address, phone, salary, department) {
        var _this = _super.call(this, name, age, address, phone) || this;
        _this.salary = salary;
        _this.department = department;
        return _this;
    }
    Employee.prototype.employeeDetails = function () {
        console.log("\n        Employee Details:\n                    Name: ".concat(this.name, "\n                    Age: ").concat(this.age, "\n                    Address: ").concat(this.address, "\n                    Phone: ").concat(this.phone, "\n                    Salary: ").concat(this.salary, "\n                    Department: ").concat(this.department));
    };
    return Employee;
}(PersonClass));
var employee1 = new Employee("John Doe", 22, "New York", 1234567890, 10000, "IT");
employee1.employeeDetails();
employee1.greet();
