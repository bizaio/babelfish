import {BabelfishFormControl} from './babelfish-form-control';
import {SelectItem} from 'primeng/api';
import {BabelfishFormControlTypes} from '../babelfish-form-control.component';

export class BabelfishFormSelect extends BabelfishFormControl {

  options: SelectItem[];

  constructor(defaultValue, label: string, tooltip: string, validators = [], options: SelectItem[] = [], controlStyle: string = "", asyncValidators = []) {
    super(BabelfishFormControlTypes.SELECT, defaultValue, label, tooltip, validators, controlStyle, asyncValidators);

    this.options = options;
  }
}
