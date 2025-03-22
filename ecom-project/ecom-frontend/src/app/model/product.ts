export class Product {
    id: number;
    name: string;
    description: string;
    brand: string;
    price: number;
    category: string;
    releaseDate: Date;
    available: boolean;
    quantity: number;
    image: string;

    constructor(
        id: number,
        name: string,
        description: string,
        brand: string,
        price: number,
        category: string,
        releaseDate: Date,
        available: boolean,
        quantity: number,
        image: string
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.releaseDate = releaseDate;
        this.available = available;
        this.quantity = quantity;
        this.image = image;
    }
}