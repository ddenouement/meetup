import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AdminBagesService} from "./admin-bages.service";
import {LanguagesList} from "../models/languagesList";
import {Badge} from "../models/badge";
import {UserToSubscribe} from "../models/userToSubscribe";

@Component({
  selector: 'app-admin-bages',
  templateUrl: './admin-bages.component.html',
  styleUrls: ['./admin-bages.component.scss']
})
export class AdminBagesComponent implements OnInit {

  selected: Badge[];
  badgeForm: FormGroup;
  allBadges: Badge[];
  loadingSave = false;
  loadingTest = false;
  loadingDelete = false;
  delete = false;
  scriptToTest: string;
  test = false;
  testCorrect = false;
  users: UserToSubscribe [] = [];


  constructor(private formBuilder: FormBuilder, private  adminService: AdminBagesService) {
  }

  ngOnInit() {
    this.test = false;
    this.testCorrect = false;
    this.badgeForm = this.formBuilder.group({
      badge: [''],
      name: ['', Validators.required],
      script: ['', Validators.required]
    });

    this.adminService.getBadges().subscribe(res => {
        // @ts-ignore
        this.allBadges = res;
      },
      err => {
        console.warn('ERROR in grt Badges');
        console.warn(err);
      });
  }

  onTest(badge: string) {
    this.loadingTest = true;
    if (badge === undefined) {
      this.scriptToTest = this.badgeForm.get('script').value;
    } else {
      this.scriptToTest = badge;
    }
    this.adminService.testScript(this.scriptToTest).subscribe((res: { users: UserToSubscribe[] }) => {
      // @ts-ignore
      this.users = res;
      this.test = true;
      this.testCorrect = true;
      this.loadingTest = false;
    }, error => {
      console.warn(error);
      this.loadingTest = false;
      this.testCorrect = false;
      this.test = true;
    });
  }

  onSave(id: number) {
    this.loadingSave = true;
    if (id === undefined) {
      const badge = <Badge>{
        name: this.scriptToTest = this.badgeForm.get('name').value,
        script: this.scriptToTest = this.badgeForm.get('script').value,
      };
      this.adminService.createBadge(badge).subscribe(res => {
        this.ngOnInit();
        this.loadingSave = false;
      }, error => {
        console.warn('ERROR in updateScript');
        console.warn(error);
        this.loadingSave = false;
        this.testCorrect = false;
        this.test = true;
      });
    }else {
      const badge = <Badge>{
        id: id,
        name: this.scriptToTest = this.badgeForm.get('name').value,
        script: this.scriptToTest = this.badgeForm.get('script').value,
      };
      this.adminService.updateBadge(id, badge).subscribe(res => {
        this.ngOnInit();
        this.loadingSave = false;
      }, error => {
        console.warn('ERROR in updateScript');
        console.warn(error);
        this.loadingSave = false;
      });
    }
  }

  onSelect() {
    this.test = false;
    this.delete = false;
  }

  onDelete(id: number) {
    this.loadingDelete = true;
    this.adminService.deleteBadge(id).subscribe(res=>{
      this.ngOnInit();
      this.loadingDelete = false;
      this.delete = true;
    },error => {
      console.warn(error);
      this.loadingSave = false;
    });
  }
}

