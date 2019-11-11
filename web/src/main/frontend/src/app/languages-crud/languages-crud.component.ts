import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {Languagesservice} from "../services/languagessservice";
import {Language} from '../models/language';
import {MatSnackBar} from "@angular/material/snack-bar";


@Component({
  selector: 'app-languages-crud',
  templateUrl: './languages-crud.component.html',
  styleUrls: ['./languages-crud.component.scss'],
  providers: [Languagesservice]
})
export class LanguagesCrudComponent  implements OnInit {
  @ViewChild('readOnlyTemplate', {static: false}) readOnlyTemplate: TemplateRef<any>;
  @ViewChild('editTemplate', {static: false}) editTemplate: TemplateRef<any>;

  editedLanguage: Language;
  languages: Array<Language>;
  isNewRecordL: boolean;
  statusMessageL: string;

  constructor(private snackBar:MatSnackBar, private serv: Languagesservice) {
    this.languages = new Array<Language>();
    this.isNewRecordL=false;

  }

  ngOnInit() {
    this.loadLanguages();
  }

  private loadLanguages() {
    this.serv.getLanguages()
      .subscribe(
        languages => {
          this.languages = languages;
        },
        err => {

          this.snackBar.open(err.error);
        });
  }
  addLanguage() {
    this.editedLanguage = new Language(0,"");
    this.languages.unshift(this.editedLanguage);
    this.isNewRecordL = true;
  }

  editLanguage(l: Language) {
    this.cancelL();
    this.editedLanguage = new Language( l.id,l.name);
  }
  loadTemplateL(l: Language) {
    if (this.editedLanguage && this.editedLanguage.id == l.id) {
      return this.editTemplate;
    } else {
      return this.readOnlyTemplate;
    }
  }
  saveLanguage() {
    if(this.editedLanguage.name.length==0){

      this.snackBar.open('Please enter valid name');
    }
    if (this.isNewRecordL) {
      this.serv.createLanguage(this.editedLanguage).subscribe(data => {


          this.snackBar.open('Saved');
            this.languages.unshift(data);
        },
        err => {

          this.snackBar.open(err.error);
        });
      this.isNewRecordL = false;
      this.languages.splice(this.languages.indexOf(this.editedLanguage), 1),
        this.editedLanguage = null;
    }
    else {
      const index = this.languages.findIndex(x => x.id == this.editedLanguage.id);
      const nl = <Language>{id: this.editedLanguage.id, name:this.editedLanguage.name };
      this.serv.updateLanguage(nl.id, nl).subscribe(data => {

          this.snackBar.open('Edited');
            this.languages[index] =  data;
        },
        err => {

          this.snackBar.open(err.error);
        });
      this.editedLanguage = null;
    }
  }

  cancelL() {
    if (this.isNewRecordL) {
      this.languages.shift();
      this.isNewRecordL = false;
    }
    this.editedLanguage = null;
  }
  deleteLanguage(l: Language) {
    this.serv.deleteLanguage(l.id).subscribe(data => {

        this.snackBar.open('Deleted');
          this.languages.splice(this.languages.indexOf(l),1);
        //  this.loadLanguages();
      },
      err => {
        this.snackBar.open(err.error);
      });
  }

  isDisabledAddButton() {
    if (this.editedLanguage!=null) return true;
  }
}
