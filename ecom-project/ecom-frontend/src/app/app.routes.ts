import { Routes } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductDetailsComponent } from './product-details/product-details.component';

export const routes: Routes = [
    {path: '', component: ProductListComponent},
    {path: 'product/:productId', component: ProductDetailsComponent}
];
