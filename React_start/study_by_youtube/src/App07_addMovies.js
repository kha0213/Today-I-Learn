import './App.css';
import Movie from "./conponents/Movie";
import {useState} from "react";

function App() {
    const [movieTitle, setMovieTitle] = useState('');
    const [movieYear, setMovieYear] = useState('');
    const [movieId, setMovieId] = useState('');
    const [movies, setMovies] = useState([
        {title: 'Iron man', year: 2001, id: Date.now()},
        {title: 'Mission Impossible', year: 2002, id: Date.now()},
        {title: 'Mother', year: 2008, id: Date.now()},
        {title: 'Spider man', year: 2020, id: Date.now()}
        ]
    );

    const removeMovie = id => {
        setMovies(movies.filter(movie => movie.title !== id));
    }

    const renderMovies = movies.length ? movies.map(movie => {
        return (
            <Movie movie={movie}
                   key={movie.title}
                   id={movie.id}
                   removeMovie={ removeMovie }/>
        );
    }) : '추가된 영화가 없습니다.';

    const addMovie = (event) => {
        event.preventDefault();
        if(!movieTitle || !movieYear)
            return;
        setMovies([...movies, {
            title: movieTitle,
            year: movieYear,
            id: movieId
        }])
        setMovieTitle('');
        setMovieYear('');
    }

    return (
    <div className="App">
        <h1>Movie List ! </h1>
        {renderMovies}
        <form onSubmit={addMovie}>
            <input type="text" placeholder={"영화제목"}
                   value={movieTitle}
                onChange={e => setMovieTitle(e.target.value)}/>
            <input type="text" placeholder={"영화개봉년도"}
                   value={movieYear}
                onChange={e => setMovieYear(e.target.value)}/>
            <button type={"submit"}>영화추가</button>
        </form>
    </div>
  );
}

export default App;
