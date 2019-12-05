import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { NavbarComponent } from './navbar/navbar.component';

import { ErrorInterceptor } from './helpers/error.interceptor';
import { AuthGuard } from './helpers/auth.guard';

import { Routes, RouterModule } from '@angular/router';
import { fakeBackendProvider } from './helpers/fakedb';
import { ToolsComponent } from './tools/tools.component';
import { ProjectsComponent } from './projects/projects.component';

import { NgxPaginationModule } from 'ngx-pagination';
import { AdminUsersComponent } from './admin-users/admin-users.component';

import { NgxsModule } from '@ngxs/store';
import { ScanState } from './states/scan.state';
import { UserState } from './states/user.state';

const routes: Routes = [
  {
    path: '',
    component: ToolsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'tools',
    component: ToolsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'projects',
    component: ProjectsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'users',
    component: AdminUsersComponent,
    canActivate: [AuthGuard]
  },
  {
    path: '**',
    redirectTo: ''
  }
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavbarComponent,
    ToolsComponent,
    ProjectsComponent,
    AdminUsersComponent
  ],
  imports: [
    NgxPaginationModule,
    FormsModule,
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    NgxsModule.forRoot([
      ScanState,
      UserState
    ])
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
