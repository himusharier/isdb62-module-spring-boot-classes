import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login/login.component';
import { DashboardComponent } from './pages/dashboard/dashboard/dashboard.component';
import { authGuard } from './core/auth.guard';
import { AdminViewComponent } from './pages/admin-view/admin-view.component';
import { CommonViewComponent } from './pages/common-view/common-view.component';
import { TeacherViewComponent } from './pages/teacher-view/teacher-view.component';
import { StudentViewComponent } from './pages/student-view/student-view.component';
import { MainLayoutComponent } from './pages/common/main-layout/main-layout.component';

export const routes: Routes = [
    {
        path: 'login',
        component: LoginComponent,
    },
    {
        path: 'dashboard',
        component: MainLayoutComponent,
        canActivate: [authGuard],
        children: [
            {
                path: 'student-view',
                component: StudentViewComponent,
                data: { roles: ['student', 'admin', 'teacher'] },
                canActivate: [authGuard]
            },
            {
                path: 'teacher-view',
                component: TeacherViewComponent,
                data: { roles: ['teacher', 'admin'] },
                canActivate: [authGuard]
            },
            {
                path: 'admin-view',
                component: AdminViewComponent,
                data: { roles: ['admin'] },
                canActivate: [authGuard]
            },
            {
                path: 'common',
                component: CommonViewComponent,
                canActivate: [authGuard],
                data: { roles: ['student', 'teacher', 'admin'] }
            },
            {
                path: '',
                redirectTo: 'common',
                pathMatch: 'full'
            }
        ]
    },
    {
        path: '**',
        redirectTo: 'login'
    }
];
