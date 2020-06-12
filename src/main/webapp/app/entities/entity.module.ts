import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'library-user',
                loadChildren: () => import('./library-user/library-user.module').then(m => m.LibraryLibraryUserModule)
            },
            {
                path: 'author',
                loadChildren: () => import('./author/author.module').then(m => m.LibraryAuthorModule)
            },
            {
                path: 'book',
                loadChildren: () => import('./book/book.module').then(m => m.LibraryBookModule)
            },
            {
                path: 'copy-book',
                loadChildren: () => import('./copy-book/copy-book.module').then(m => m.LibraryCopyBookModule)
            },
            {
                path: 'loan',
                loadChildren: () => import('./loan/loan.module').then(m => m.LibraryLoanModule)
            },
            {
                path: 'student-type',
                loadChildren: () => import('./student-type/student-type.module').then(m => m.LibraryStudentTypeModule)
            },
            {
                path: 'subject',
                loadChildren: () => import('./subject/subject.module').then(m => m.LibrarySubjectModule)
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ]
})
export class LibraryEntityModule {}
