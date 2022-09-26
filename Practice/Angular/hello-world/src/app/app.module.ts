import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { TempComponentComponent } from './temp-component/temp-component.component';
import { SecondComponentComponent } from './second-component/second-component.component';
// import { EmployeeServiceService } from './employee-service.service';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { DepartmentDetailsComponent } from './department-details/department-details.component';
import { DepartmentOverviewComponent } from './department-overview/department-overview.component';
import { DepartmentContactComponent } from './department-contact/department-contact.component';

@NgModule({
  declarations: [
    AppComponent,
    TempComponentComponent,
    SecondComponentComponent,
    routingComponents,
    PageNotFoundComponent,
    DepartmentDetailsComponent,
    DepartmentOverviewComponent,
    DepartmentContactComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [
    // EmployeeServiceService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
