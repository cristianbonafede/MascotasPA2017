import { Component, OnInit } from '@angular/core';
import { MascotaService, Mascota } from './mascota.service';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { Router } from '@angular/router';
import { DatePickerPipe } from '../tools/common-pipes.pipe';
import { DatePickerModule } from 'ng2-datepicker';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-nueva-mascota',
  templateUrl: './nueva-mascota.component.html'
})
export class NuevaMascotaComponent implements OnInit {
  mascota: Mascota;
  errorMessage: string;
  formSubmitted: boolean;
  errors: string[] = [];
   public file_srcs: string[] = [];
   public debug_size_before: string[] = [];
   public debug_size_after: string[] = [];
  constructor(private mascotasService: MascotaService, private changeDetectorRef: ChangeDetectorRef,
    private route: ActivatedRoute, private router: Router) {
    this.mascota = { id: null, nombre: '', fechaNacimiento: '', descripcion: '', imagen: '' };
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      let id = +params['id'];
      if (id) {
        this.mascotasService.buscarMascota(id)
          .then(mascota => this.mascota = mascota)
          .catch(error => this.errorMessage = <any>error);
      }
    });
  }
  

resize(img, MAX_WIDTH:number, MAX_HEIGHT:number, callback){
  // This will wait until the img is loaded before calling this function
  return img.onload = () => {

    // Get the images current width and height
    var width = img.width;
    var height = img.height;

    // Set the WxH to fit the Max values (but maintain proportions)
    if (width > height) {
        if (width > MAX_WIDTH) {
            height *= MAX_WIDTH / width;
            width = MAX_WIDTH;
        }
    } else {
        if (height > MAX_HEIGHT) {
            width *= MAX_HEIGHT / height;
            height = MAX_HEIGHT;
        }
    }

    // create a canvas object
    var canvas = document.createElement( "canvas" );

    // Set the canvas to the new calculated dimensions
    canvas.width = width;
    canvas.height = height;
    var ctx = canvas.getContext("2d");

    ctx.drawImage(img, 0, 0,  width, height);

    // Get this encoded as a jpeg
    // IMPORTANT: 'jpeg' NOT 'jpg'
    var dataUrl = canvas.toDataURL('image/jpeg');

    // callback with the results
    callback(dataUrl, img.src.length, dataUrl.length);
  };
}
    readFile(file, reader, callback) {
        reader.onload = () => {
            callback(reader.result);
            this.mascota.imagen= reader.result;
            console.log(reader.result);
        }
        reader.readAsDataURL(file);
    }

readFiles(files, index=0){
  // Create the file reader
  let reader = new FileReader();

  // If there is a file
  if(index in files){
    // Start reading this file
    this.readFile(files[index], reader, (result) =>{
      // Create an img element and add the image file data to it
      var img = document.createElement("img");
      img.src = result;

      // Send this img to the resize function (and wait for callback)
      this.resize(img, 50, 50, (resized_jpeg, before, after) => {
        // For debugging (size in bytes before and after)
        this.debug_size_before.push(before);
        this.debug_size_after.push(after);

        // Add the resized jpeg img source to a list for preview
        // This is also the file you want to upload. (either as a
        // base64 string or img.src = resized_jpeg if you prefer a file). 
        this.file_srcs.push(resized_jpeg);

        // Read the next file;
      //  this.readFiles(files, index+1);
      });
    });
  }else{
    // When all files are done This forces a change detection
    this.changeDetectorRef.detectChanges();
  }
}



fileChange(input){
  this.readFiles(input.files);
}


  submitForm() {
    this.cleanRestValidations();
    this.mascotasService.guardarMascota(this.mascota)
      .then(mascota => this.router.navigate(['/mascotas']))
      .catch(error => this.procesarValidacionesRest(error));
  }


  onDelete() {
    this.cleanRestValidations();
    this.mascotasService.eliminarMascota(this.mascota.id)
      .then(any => this.router.navigate(['/mascotas']))
      .catch(error => this.procesarValidacionesRest(error));
  }

  cleanRestValidations() {
    this.errorMessage = undefined;
    this.errors = [];
  }

  procesarValidacionesRest(data: any) {
    if (data.message) {
      for (const error of data.message) {
        this.errors[error.path] = error.message;
      }
    } else {
      this.errorMessage = data.message;
    }
  }
}
