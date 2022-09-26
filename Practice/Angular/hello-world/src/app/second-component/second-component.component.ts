import { Component, OnInit } from '@angular/core';
import { EmployeeServiceService } from '../employee-service.service';

@Component({
  selector: 'app-second-component',
  styles: [
    `
      h1 {
        color: red;
      }
    `,
  ],

  template: `
    <h1>Welcome to 2nd Component</h1>

    <!-- show all employees -->
    <h2>All Employees</h2>
    <p>{{ errorMsg }}</p>
    <ul *ngFor="let employee of employees">
      <li>{{ employee.id }}. {{ employee.name }} - {{ employee.age }}</li>
    </ul>
  `,
})
export class SecondComponentComponent implements OnInit {
  public employees: any[] = [];
  public errorMsg: string = '';

  constructor(private _employeeService: EmployeeServiceService) {}

  ngOnInit(): void {
    this._employeeService.getEmployees().subscribe({
      next: (data) => (this.employees = data),
      error: (err) => (this.errorMsg = err),
    });
  }
}
