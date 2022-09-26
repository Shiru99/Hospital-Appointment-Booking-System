import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-department-list',
  template: `
    <h2>All Departments</h2>

    <!-- show all departments -->
    <ul class="items">
      <li
        (click)="onSelect(department)"
        [class.selected]="isSelected(department)"
        *ngFor="let department of departments"
      >
        <span class="badge">{{ department.id }}</span> {{ department.name }}
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
        width: 150px;
      }

      .selected {
        background-color: #cfd8dc !important;
        color: white;
      }

      .badge {
        background-color: #607d8b;
        color: white;
        padding: 0.5em 0.7em;
        border-radius: 0.5em;
      }

      .items {
        margin: auto;
        list-style-type: none;
        width: 15em;
      }

      .items li {
        cursor: pointer;
        position: relative;
        left: 0;
        background-color: #eee;
        margin: 0.5em;

        border-radius: 4px;
      }

      .items li.selected {
        background-color: #607d8b !important;
        color: white;
      }

      .items li:hover {
        color: white;
        background-color: #ddd;
        left: 0.1em;
      }

      .items .text {
        position: relative;
        top: -3px;
      }

      .items .badge {
        display: inline-block;
        font-size: small;
        color: white;
        padding: 0.8em 0.7em 0 0.7em;
        background-color: #607d8b;

        line-height: 1em;
        position: relative;
        left: -1px;
        height: 1.8em;
        margin-right: 0.8em;
        border-radius: 4px 0 0 4px;
      }
    `,
  ],
})
export class DepartmentListComponent implements OnInit {
  public departments: any[] = [
    { id: 1, name: 'Angular' },
    { id: 2, name: 'Node' },
    { id: 3, name: 'MongoDB' },
    { id: 4, name: 'Ruby' },
    { id: 5, name: 'Bootstrap' },
  ];

  onSelect(department: any) {
    // this.router.navigate(['/departments', department.id]);
    this.router.navigate([department.id], { relativeTo: this.route });
  }

  constructor(private router: Router, private route: ActivatedRoute) {}

  public selectedId: any;

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.selectedId = params.get('id');
    });
  }

  isSelected(department: any) {
    return department.id.toString() === this.selectedId;
  }
}
