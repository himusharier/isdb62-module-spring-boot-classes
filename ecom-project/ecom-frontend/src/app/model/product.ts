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

    constructor(
        id: number,
        name: string,
        description: string,
        brand: string,
        price: number,
        category: string,
        releaseDate: Date,
        available: boolean,
        quantity: number
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
    }
}