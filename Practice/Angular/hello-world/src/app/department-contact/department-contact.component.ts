import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-department-contact',
  template: ` <p>department-contact works!</p> `,
  styles: [
    `
      p {
        color: red;
        text-align: center;
      }
    `,
  ],
})
export class DepartmentContactComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}
}
