import React, {useState} from 'react';
import Movie from "../conponents/Movie";
import InputField from "../conponents/InputField";

const Movies = () => {
    const [titleError, setTitleError] = useState('');
    const [yearError, setYearError] = useState('');
    const [movieTitle, setMovieTitle] = useState('');
    const [movieYear, setMovieYear] = useState('');
    const [movieId, setMovieId] = useState(5);
    const [movies, setMovies] = useState([
            {title: 'Iron man', year: 2001, id: 1},
            {title: 'Mission Impossible', year: 2002, id: 2},
            {title: 'Mother', year: 2008, id: 3},
            {title: 'Spider man', year: 2020, id: 4}
        ]
    );

    const removeMovie = id => {
        setMovies(movies.filter(movie => movie.id !== id));
    }

    const renderMovies = movies.length ? movies.map(movie => {
        return (
            <Movie movie={movie}
                   key={movie.id}
                   removeMovie={ removeMovie }/>
        );
    }) : '추가된 영화가 없습니다.';

    const addMovie = (event) => {
        event.preventDefault();
        setTitleError(movieTitle?'':'영화제목을 입력해주세요');
        setYearError(movieYear?'':'영화개봉년도를 입력해주세요');
        if(!movieTitle || !movieYear) {
            return;
        }
        setMovies([...movies, {
            title: movieTitle,
            year: movieYear,
            id: movieId
        }])
        allClear();

        setMovieId(movieId + 1);
        console.log('movie Id : ', movieId)
    }

    function errorClear() {
        setYearError('');
        setTitleError('');
    }

    function inputClear() {
        setMovieTitle('');
        setMovieYear('');
    }

    function allClear() {
        inputClear();
        errorClear();
    }

    return (
        <div className="App">
            <h1>Movie List ! </h1>
            {renderMovies}
            <form onSubmit={addMovie}>
                <InputField
                    type="text"
                    value={movieTitle}
                    placeholder="영화제목"
                    onChange={e => setMovieTitle(e.target.value)}
                    errorMessage={titleError}
                />
                <InputField
                    type="number"
                    value={movieYear}
                    placeholder="영화개봉년도"
                    onChange={e => setMovieYear(e.target.value)}
                    errorMessage={yearError}
                />
                <button type={"submit"}>영화추가</button>
            </form>
        </div>
    );
}
export default Movies;