import { Routes } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { ProductAddComponent } from './product-add/product-add.component';
import { ProductUpdateComponent } from './product-update/product-update.component';
import { ProductSearchComponent } from './product-search/product-search.component';

export const routes: Routes = [
    {path: '', component: ProductListComponent},
    {path: 'product/:productId', component: ProductDetailsComponent},
    {path: 'add-product', component: ProductAddComponent},
    {path: 'product/:productId/edit', component: ProductUpdateComponent},
    {path: 'products/search', component: ProductSearchComponent}
];
