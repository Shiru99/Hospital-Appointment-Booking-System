export {}

function personDetail(name: string, age: number, address: string, phone: number) {
    console.log(`
    Person Details:
                Name: ${name}
                Age: ${age}
                Address: ${address}
                Phone: ${phone}`);
}

personDetail("John Doe", 22, "New York", 1234567890);




// Interface

interface Person {
    name: string;
    age: number;    
    address?: string;       // optional
    phone?: number;         // optional
}

function personDetails(person: Person) {
    console.log(`
    Person Details:
                Name: ${person.name}
                Age: ${person.age}
                Address: ${person.address}
                Phone: ${person.phone}`);
}

let person1 = {
    name: "John Doe",
    age: 22,
    address: "New York",
    phone: 1234567890
}

personDetails(person1);


// Class

class PersonClass {
    public name: string;
    public age: number;
    protected address?: string;
    protected phone?: number;

    constructor(name: string, age: number, address?: string, phone?: number) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
    }

    personDetails() {
        console.log(`
        Person Details:
                    Name: ${this.name}
                    Age: ${this.age}
                    Address: ${this.address}
                    Phone: ${this.phone}`);
    }

    greet() {
        console.log(`Hello ${this.name}`);
    }
}

let person2 = new PersonClass("John Doe", 22, "New York", 1234567890);
person2.personDetails();

// Inheritance

class Employee extends PersonClass {
    private salary: number;
    department: string;

    constructor(name: string, age: number, address: string, phone: number, salary: number, department: string) {
        super(name, age, address, phone);
        this.salary = salary;
        this.department = department;
    }

    employeeDetails() {
        console.log(`
        Employee Details:
                    Name: ${this.name}
                    Age: ${this.age}
                    Address: ${this.address}
                    Phone: ${this.phone}
                    Salary: ${this.salary}
                    Department: ${this.department}`);
    }
}

let employee1 = new Employee("John Doe", 22, "New York", 1234567890, 10000, "IT");
employee1.employeeDetails();
employee1.greet();
