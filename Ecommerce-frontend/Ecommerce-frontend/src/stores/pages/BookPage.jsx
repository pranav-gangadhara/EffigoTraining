import React, { useState } from 'react';
import { booksData } from '../data/books';
import Navbar from '../components/Navbar';
import { Link } from 'react-router-dom';

const BookPage = () => {
    const [selectedCategories, setSelectedCategories] = useState([]);

    const categoryHandler = (category) => {
        if (selectedCategories.includes(category)) {
            setSelectedCategories(selectedCategories.filter(item => item !== category));
        } else {
            setSelectedCategories([...selectedCategories, category]);
        }
    };

    const filteredBooks = selectedCategories.length === 0
        ? booksData
        : booksData.filter((book) => selectedCategories.includes(book.category));

    return (
        <>
            <Navbar />
            <div className="fullpage">
                <div className="pro-selected">
                    {booksData
                        .map(book => book.category)
                        .filter((value, index, self) => self.indexOf(value) === index)
                        .map(category => (
                            <div className='pro-input' key={category}>
                                <label>
                                    <input 
                                        type="checkbox" 
                                        checked={selectedCategories.includes(category)}
                                        onChange={() => categoryHandler(category)}
                                    />
                                    {category}
                                </label>
                            </div>
                        ))}
                </div>

                <div className='pageSection'>
                    {filteredBooks.map((book) => (
                        <div key={book.id}>
                            <Link to={`/books/${book.id}`}>
                                <div className="pageImg">
                                    <img src={book.image} alt={book.title} />
                                </div>
                            </Link>
                            <div className="proModel">
                                {book.title} - {book.author}
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </>
    );
};

export default BookPage;
