import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-department-overview',
  template: `
    <p>
      department-overview works!
    </p>
  `,
  styles: [`
    p {
      color: red;
      text-align: center;
    }`
  ]
})
export class DepartmentOverviewComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
