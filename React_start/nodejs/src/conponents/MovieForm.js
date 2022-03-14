import React, {useState} from "react";

const MovieForm = ({addMovie}) => {
    const [movieTitle, setMovieTitle] = useState('');
    const [movieYear, setMovieYear] = useState('');
    const [titleError, setTitleError] = useState('');
    const [yearError, setYearError] = useState('');

    const resetForm = () => {
        setMovieTitle('');
        setMovieYear('');
    }

    const validateForm = () => {
        let validated = true;
        if (!movieTitle) {
            setTitleError('영화 제목을 입력하세요');
            validated = false;
        } else {
            setTitleError('');
        }

        if(!movieYear) {
            setYearError('개봉년도를 넣어주세요');
            validated = false;
        } else {
            setYearError('');
        }

        return validated;
    }

    const onSubmit = (event) => {
        event.preventDefault();
        if(validateForm()) {
            addMovie({
                id: Date.now(),
                title: movieTitle,
                year: movieYear
            });
            resetForm();
        }
    }
    return (
    <form onSubmit={onSubmit}>
        <input type="text" placeholder={"영화제목"}
               value={movieTitle}
               onChange={e => setMovieTitle(e.target.value)}/>
        <div className={"error"}>{titleError}</div>
        <input type="number" placeholder={"영화개봉년도"}
               value={movieYear}
               onChange={e => setMovieYear(e.target.value)}/>
        <div className={"error"}>{yearError}</div>
        <button type={"submit"}>영화추가</button>
    </form>);
}
export default MovieForm;
