import {BabelfishFormControl} from './babelfish-form-control';
import {SelectItem} from 'primeng/api';
import {BabelfishFormControlTypes} from '../babelfish-form-control.component';

export class BabelfishFormSelectMulti extends BabelfishFormControl {

  options: SelectItem[];

  constructor(defaultValue, label: string, validators = [], options: SelectItem[] = [], controlStyle: string = "", asyncValidators = []) {
    super(BabelfishFormControlTypes.SELECT_MULTI, defaultValue, label, '', validators, controlStyle, asyncValidators);

    this.options = options;
  }
}
