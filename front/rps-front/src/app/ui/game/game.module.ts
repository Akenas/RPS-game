import { NgModule } from "@angular/core";
import { GameComponent } from "./game.component";
import { OptionPickerComponent } from "./option-picker/option-picker.component";
import { PickVisorComponent } from "./pick-visor/pick-visor.component";
import { QueueControllerComponent } from "./queue-controller/queue-controller.component";
import { RoundsVisorComponent } from "./rounds-visor/rounds-visor.component";
import { CommonModule } from "@angular/common";


@NgModule({
    declarations: [
        OptionPickerComponent,
        PickVisorComponent,
        QueueControllerComponent,
        RoundsVisorComponent
    ],
    imports: [
        RoundsVisorComponent,
        OptionPickerComponent,
        PickVisorComponent
  ],
    exports: [
        GameComponent
    ]
  })
  export class GamesModule { }