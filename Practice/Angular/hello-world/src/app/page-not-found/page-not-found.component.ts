import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-page-not-found',
  template: ` 
    <h1>404</h1>
    <h3>Page Not Found</h3> 
  `,
  styles: [
    `
      h1 {
        color: red;
        text-align: center;
        font-size: 100px;
      }
      h3 {
        font-size: 50px;
        color: black;
        text-align: center;
      }
    `,
  ],
})
export class PageNotFoundComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
