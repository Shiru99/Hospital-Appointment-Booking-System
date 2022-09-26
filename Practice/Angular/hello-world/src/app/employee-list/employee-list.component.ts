import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-employee-list',
  template: `
    <h2>All Employees</h2>

    <!-- show all employees -->
    <ul class="items">
      <li *ngFor="let employee of employees">
        <span class="badge">{{ employee.id }}</span> {{ employee.name }}
      </li>
    </ul>
  `,

  styles: [
    `
      h2 {
        color: red;
        text-align: center;
      }

      li {
        margin: 20px auto;
        width: 100px;
      }
    `,
  ],
})
export class EmployeeListComponent implements OnInit {
  public employees: any[] = [
    { id: 1, name: 'Tom', age: 25 },
    { id: 2, name: 'Alex', age: 30 },
    { id: 3, name: 'Mike', age: 28 },
    { id: 4, name: 'Mary', age: 26 },
    { id: 5, name: 'John', age: 27 },
  ];

  constructor() {}

  ngOnInit(): void {}
}
