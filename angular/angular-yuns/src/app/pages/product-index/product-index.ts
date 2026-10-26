import { Component } from '@angular/core';
import { ProductDto } from '../../models/product-dto';
import { Confirm } from '../../confirm/confirm';

@Component({
  imports: [Confirm],
  selector: 'app-product-index',
  styleUrl: './product-index.scss',
  templateUrl: './product-index.html',
})
export class ProductIndex {
  private nextId = 1;
  products: ProductDto[] = [];

  constructor() {
    console.log('In ProductIndex constructor');

    this.products.push({
      id: this.nextId++,
      name: 'Tennis Wilson',
      description: "Wilson tennis",
      price: 0.99,
      imageUrl: 'https://www.e-tennis.com/media/catalog/product/cache/9f6a0f2a2b1968473f9efdabe1103d96/w/i/wilson-championship-tennis-balls-x-3.webp',
    });

    this.products.push({
      id: this.nextId++,
      name: 'The real Wilson',
      description: "Wilson LE VRAI",
      price: 9999,
      imageUrl: 'https://i.pinimg.com/474x/4b/87/5d/4b875d819aa7874a85547e15da117d4f.jpg',
    });


    this.products.push({
      id: this.nextId++,
      name: 'Basketball Wilson',
      description: 'Wilson BALLE de basketball',
      price: 9.99,
      imageUrl: 'https://cdn.shopify.com/s/files/1/0996/7373/5482/files/WZ2016501XB_0_7_NBA_AUTHENTIC_INDOOR_OUTDOOR_BSKT_295_BROWN_png_genericNbaLeague_01_350x.png?v=1789764265&width=350',
    });

    this.products.push({
      id: this.nextId++,
      name: 'Baseball Wilson',
      description: 'Wilson Balle de Baseball',
      price : 99.99,
      imageUrl: 'https://www.wilson.com/cdn/shop/files/WTA1060B_0_SST_Official_League_baseball_png_white_01.png?v=1789767273&width=1920'
    })

  }
}

