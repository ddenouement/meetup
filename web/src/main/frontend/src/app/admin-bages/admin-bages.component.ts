import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AdminBagesService} from "./admin-bages.service";
import {LanguagesList} from "../models/languagesList";
import {Badge} from "../models/badge";
import {UserToSubscribe} from "../models/userToSubscribe";
import {ISubscription, Subscription} from "rxjs-compat/Subscription";
import {SubscriptionLike} from "rxjs";

@Component({
  selector: 'app-admin-bages',
  templateUrl: './admin-bages.component.html',
  styleUrls: ['./admin-bages.component.scss']
})
export class AdminBagesComponent implements OnInit, OnDestroy {
  private subscriptions: Subscription = new Subscription();
  private subscriptionGetBadges: ISubscription;
  private selected: Badge[];
  private badgeForm: FormGroup;
  private allBadges: Badge[];
  private loadingSave = false;
  private loadingTest = false;
  private loadingDelete = false;
  private delete = false;
  private scriptToTest: string;
  private test = false;
  private testCorrect = false;
  private users: UserToSubscribe [] = [];


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
    this.subscriptionGetBadges = this.adminService.getBadges().subscribe(res => {
        // @ts-ignore
        this.allBadges = res;
      },
      err => {
        console.warn('ERROR in grt Badges');
        console.warn(err);
      });
    this.subscriptions.add(this.subscriptionGetBadges);
  }

  onTest() {
    this.loadingTest = true;
    this.scriptToTest = this.badgeForm.get('script').value;
    this.subscriptions.add(this.adminService.testScript(this.scriptToTest).subscribe((res: { users: UserToSubscribe[] }) => {
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
    }));
  }

  onSave(id: number) {
    this.loadingSave = true;
    if (id === undefined) {
      const badge = <Badge>{
        name: this.scriptToTest = this.badgeForm.get('name').value,
        script: this.scriptToTest = this.badgeForm.get('script').value,
      };
      this.subscriptions.add(this.adminService.createBadge(badge).subscribe(res => {
        this.ngOnInit();
        this.loadingSave = false;
      }, error => {
        console.warn('ERROR in updateScript');
        console.warn(error);
        this.loadingSave = false;
        this.testCorrect = false;
        this.test = true;
      }));
    } else {
      const badge = <Badge>{
        id: id,
        name: this.scriptToTest = this.badgeForm.get('name').value,
        script: this.scriptToTest = this.badgeForm.get('script').value,
      };
      this.subscriptions.add(this.adminService.updateBadge(id, badge).subscribe(res => {
        this.ngOnInit();
        this.loadingSave = false;
      }, error => {
        console.warn('ERROR in updateScript');
        console.warn(error);
        this.loadingSave = false;
      }));
    }
  }

  onSelect() {
    this.test = false;
    this.delete = false;
  }

  onDelete(id: number) {
    this.loadingDelete = true;
    this.subscriptions.add(this.adminService.deleteBadge(id).subscribe(res => {
      this.ngOnInit();
      this.loadingDelete = false;
      this.delete = true;
    }, error => {
      console.warn(error);
      this.loadingSave = false;
    }));
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }
}

