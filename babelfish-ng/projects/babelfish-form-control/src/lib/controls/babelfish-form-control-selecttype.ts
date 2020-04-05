import {BabelfishFormControl} from './babelfish-form-control';
import {SelectItem} from 'primeng/api';
import {BabelfishFormControlTypes} from '../babelfish-form-control.component';

export class BabelfishFormSelectType extends BabelfishFormControl {

  subType: string;

  constructor(defaultValue, label: string, validators = [], subType: string, asyncValidators = []) {
    super(BabelfishFormControlTypes.SELECTTYPE, defaultValue, label, validators, asyncValidators);

    this.subType = subType;
  }
}
