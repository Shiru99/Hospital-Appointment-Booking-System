import { Component, Input, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-temp-component',

  styles: [
    `
      .text-success {
        color: green;
      }
      .text-danger {
        color: red;
      }
      .text-special {
        font-size: 50px;
      }
    `,
  ],

  template: `
    <h1>{{ headline }}</h1>
    <button (click)="fireEvent()">send message from child</button>
    <br />
    <!-- Interpolation  -->
    <h1>Welcome, {{ name }}</h1>
    <h2>{{ name.length + 5 }}</h2>
    <h2>{{ greetUser() }}</h2>
    <h2>{{ siteUrl }}</h2>
    <!-- Pipes -->
    <br />
    <p>{{ name | lowercase }}</p>
    <p>{{ name | uppercase }}</p>
    <p>{{ name | titlecase }}</p>
    <p>{{ name | slice: 3:8 }}</p>
    <p>{{ person | json }}</p>
    <p>{{ 5.678 | number: '3.5-6' }}</p>
    <p>{{ 0.75 | percent }}</p>
    <p>{{ 0.75 | currency }}</p>
    <p>{{ 0.75 | currency: 'INR' }}</p>
    <p>{{ date }}</p>
    <p>{{ date | date: 'short' }}</p>
    <p>{{ date | date: 'shortDate' }}</p>
    <p>{{ date | date: 'shortTime' }}</p>
    

    <br />

    <!-- Property Binding -->
    <input [id]="myId" type="text" value="John Doe" />

    <input
      [disabled]="isDisabled"
      id="{{ myId }}"
      type="text"
      value="Jenny Doe"
    />
    <input
      bind-disabled="isDisabled"
      id="{{ myId }}"
      type="text"
      value="James Doe"
    />

    <!-- Class Binding -->
    <h2 [class]="successClass">John Doe</h2>
    <h2 [class.text-danger]="hasError">John Doe</h2>
    <h2 [ngClass]="multiClasses">John Doe</h2>

    <!-- Style Binding -->
    <h2 [style.color]="'orange'">Style Binding</h2>
    <h2 [style.color]="hasError ? 'red' : 'green'">Style Binding 2</h2>
    <h2 [style.color]="highlightColor">Style Binding 3</h2>
    <h2 [ngStyle]="titleStyles">Hello World</h2>

    <!-- Event Binding -->
    <h2 [style.color]="'orange'" [style.font-size]="'100px'">
      {{ greetings }}
    </h2>
    <button (click)="onClick($event)">toggle greetings</button>

    <!-- Template Reference Variables -->
    <br /><br />
    <input #myInput type="text" />
    <button (click)="logMessage(myInput)">Log</button>

    <!-- Two-way Binding -->
    <br /><br />
    <input [(ngModel)]="tempName" type="text" />
    <h1>Welcome, {{ tempName }}</h1>

    <!-- Structural Directives : ngIf If-else -->
    <br /><br />
    <h2 *ngIf="displayName; else elseBlock">John Doe</h2>
    <ng-template #elseBlock>
      <h2>Name is hidden</h2>
    </ng-template>

    <!-- Structural Directives : ngIf then-else -->
    <h2 *ngIf="!displayName; then thenBlock1; else elseBlock1"></h2>
    <ng-template #thenBlock1>
      <h2>John Doe</h2>
    </ng-template>
    <ng-template #elseBlock1>
      <h2>Name is hidden</h2>
    </ng-template>

    <!-- Structural Directives : ngSwitch -->
    <br /><br />
    <div [ngSwitch]="color">
      <div *ngSwitchCase="'red'">You picked red color</div>
      <div *ngSwitchCase="'blue'">You picked blue color</div>
      <div *ngSwitchCase="'green'">You picked green color</div>
      <div *ngSwitchDefault>Pick again</div>
    </div>

    <!-- Structural Directives : ngFor -->
    <br /><br />
    <div *ngFor="let color of colors; index as i">
      <h2>{{ i + 1 }} {{ color }}</h2>
    </div>
  `,
})
export class TempComponentComponent {


  // Component Interaction: Parent to Child
  @Input('parentData') 
  public headline : string="Hello";

  // Component Interaction: Child to Parent
  public message: string = 'Message from : app-temp-component';

  @Output()
  public childEvent = new EventEmitter();

  fireEvent() {
    this.childEvent.emit(this.message);
  }



  // Interpolation
  public name: string = 'John Doe';
  public siteUrl = window.location.href;
  public person = {
    "firstName": "John",
    "lastName": "Doe"
    };

  // pipes
  public date = new Date();  

  greetUser() {
    return 'Hello ' + this.name;
  }

  // Property Binding
  public myId: string = 'testId';
  public isDisabled: boolean = true;

  // Class Binding
  public successClass: string = 'text-success';
  public hasError: boolean = true;
  public multiClasses = {
    'text-success': !this.hasError,
    'text-danger': this.hasError,
    'text-special': true,
  };

  // Style Binding
  public highlightColor: string = 'green';
  public titleStyles = {
    color: 'blue',
    fontStyle: 'italic',
    fontSize: '50px',
  };

  // Event Binding
  public greetingsArray: Array<string> = [
    'Good Morning',
    'Good Afternoon',
    'Good Evening',
    'Good Night',
  ];
  public index: number = 0;
  public greetings: string = this.greetingsArray[0];

  onClick($event: MouseEvent) {
    this.index++;
    this.greetings =
      this.greetingsArray[this.index % this.greetingsArray.length];
  }

  // Template Reference Variables
  logMessage(input: HTMLInputElement) {
    console.log(input.value);
  }

  // Two-way Binding
  public tempName: string = 'John Doe';

  // Structural Directives : ngIf
  public displayName: boolean = false;

  // Structural Directives : ngSwitch
  public color: string = 'red';

  // Structural Directives : ngFor
  public colors: Array<string> = ['red', 'blue', 'green', 'yellow'];
}
