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
  scriptToTest: string;
  baaaaadge: Badge [] = [
    {
      id: 6,
      name: "Polyglot",
      script: "SELECT count(*) > 1 FROM users_languages WHERE id_user = $1;"
    },
    {
      id: 7,
      name: "Star",
      script: "SELECT count(*) >= 5 FROM subscriptions WHERE id_speaker = $1;"
    },
    {
      id: 43,
      name: "Megastar",
      script: "SELECT count(*) >= 5 FROM subscriptions WHERE id_speaker = $1;"
    }
  ];
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
    this.test = true;
    this.loadingTest = true;
    if (badge === undefined) {
      this.scriptToTest = this.badgeForm.get('script').value;
    } else {
      this.scriptToTest = badge;
    }
    this.adminService.testScript(this.scriptToTest).subscribe((res: { users: UserToSubscribe[] }) => {
      // @ts-ignore
      this.users = res;
      this.testCorrect = true;
      this.loadingTest = false;
    }, error => {
      console.warn(error);
      this.loadingTest = false;
      this.testCorrect = false;
    });
  }

  onSave(id: number) {
    if (id === undefined) {
      const badge = <Badge>{
        name: this.scriptToTest = this.badgeForm.get('name').value,
        script: this.scriptToTest = this.badgeForm.get('script').value,
      };
      this.adminService.createBadge(badge).subscribe(res => {
        this.ngOnInit();
      }, error => {
        console.warn('ERROR in updateScript');
        console.warn(error);
      });
    }else {
      const badge = <Badge>{
        id: id,
        name: this.scriptToTest = this.badgeForm.get('name').value,
        script: this.scriptToTest = this.badgeForm.get('script').value,
      };
      this.adminService.updateBadge(id, badge).subscribe(res => {
        this.ngOnInit();
      }, error => {
        console.warn('ERROR in updateScript');
        console.warn(error);
      });
    }
  }

  onSelect() {
    this.test = false;
  }

}

