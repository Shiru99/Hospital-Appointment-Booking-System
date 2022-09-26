import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-department-details',
  template: `
    <p>You selected department with id = {{ departmentId }}</p>
    <div>
      <a (click)="goPrevious()">Previous</a>
      <a (click)="goToOverview()">Overview</a>
      <a (click)="goToContact()">Contact</a>
      <a (click)="goNext()">Next</a>
    </div>

    <router-outlet></router-outlet>

    <div>
      <br />
      <button (click)="goToDepartments()">Back</button>
    </div>
  `,
  styles: [
    `
      p {
        text-align: center;
        color: orange;
      }
      div {
        text-align: center;
      }
      a {
        margin: auto 20px;
        text-decoration: none;
        color: blue;
      }
    `,
  ],
})
export class DepartmentDetailsComponent implements OnInit {
  public departmentId: any = 1;
  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    // this.departmentId = this.route.snapshot.paramMap.get('id');

    this.route.paramMap.subscribe((params) => {
      this.departmentId = params.get('id');
    });
  }

  goNext() {
    let nextId = parseInt(this.departmentId) + 1;
    // this.router.navigate(['/departments', nextId]);
    this.router.navigate(['../', nextId], { relativeTo: this.route });
  }
  goPrevious() {
    let previousId = parseInt(this.departmentId) - 1;
    // this.router.navigate(['/departments', previousId]);
    this.router.navigate(['../', previousId], { relativeTo: this.route });
  }

  goToDepartments() {
    let selectedId = this.departmentId ? this.departmentId : null;
    // this.router.navigate(['/departments', { id: selectedId }]);
    this.router.navigate(['../', { id: selectedId }], {
      relativeTo: this.route,
    });
  }

  goToOverview() {
    this.router.navigate(['overview'], { relativeTo: this.route });
  }

  goToContact() {
    this.router.navigate(['contact'], { relativeTo: this.route });
  }
}
