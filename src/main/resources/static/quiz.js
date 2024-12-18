const quizContainer = document.getElementById('quizContainer');
    const submitBtn = document.getElementById('submitBtn');
    const loadingElement = document.getElementById('loading');
    const errorElement = document.getElementById('error');
    const resultsContainer = document.getElementById('resultsContainer');
    const userAnswers = {};
    let quizData = [];

    async function fetchQuizData() {
        try {
            const response = await fetch('/quiz/get/1');
            if (!response.ok) {
                throw new Error('Failed to fetch quiz data');
            }
            quizData = await response.json();
            renderQuiz();
        } catch (error) {
            console.error('Error in fetching quiz data:', error);
            showError('Failed to load quiz questions');
        }
    }

    function renderQuiz() {
        if (quizData.length === 0) {
            showError('No quiz questions heree');
            return;
        }

        loadingElement.style.display = 'none';
        quizContainer.style.display = 'block';

        quizData.forEach((question, index) => {
            const questionElement = document.createElement('div');
            questionElement.className = 'question';
            questionElement.innerHTML = `
                <h3>${index + 1}. ${question.questionTitle}</h3>
                <div class="options">
                    ${['option1', 'option2', 'option3', 'option4'].map(option => `
                        <div class="option" data-question-id="${question.id}" data-option="${option}">
                            ${question[option]}
                        </div>
                    `).join('')}
                </div>
            `;
            quizContainer.appendChild(questionElement);
        });

        document.querySelectorAll('.option').forEach(option => {
            option.addEventListener('click', selectOption);
        });
    }

    function selectOption(e) {
        const id = e.target.dataset.questionId;
        const response = e.target.textContent.trim();

        e.target.parentElement.querySelectorAll('.option').forEach(opt => {
            opt.classList.remove('selected');
        });

        e.target.classList.add('selected');

        userAnswers[id] = response;

        submitBtn.disabled = Object.keys(userAnswers).length !== quizData.length;
    }

    async function submitQuiz() {
        const quizId = 1;
        const url = `/quiz/submit/${quizId}`;

        const submitData = Object.entries(userAnswers).map(([questionId, answer]) => ({
            id: parseInt(questionId),
            response: answer
        }));
        console.log('Submit data:', submitData);
        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(submitData)
            });

            if (!response.ok) {
                throw new Error('Server error');
            }

            const results = await response.json();
             console.log('Results from server:', results);
            if (!results || typeof results.score === 'undefined' || typeof results.totalQuestions === 'undefined') {
                throw new Error('Invalid response from server');
            }

            displayResults(results);
        } catch (error) {
            console.error('Error:', error);
            showError('Failed to submit quiz');
        }
    }

function displayResults(results) {
    quizContainer.style.display = 'none';
    submitBtn.style.display = 'none';
    resultsContainer.style.display = 'block';

    const { score, totalQuestions, responses } = results;


    resultsContainer.innerHTML = `
        <h2>Quiz Results</h2>
        <div class="result-score">
            Score: ${score} / ${totalQuestions}
        </div>
        <p>You did good :></p>
    `;


    const responseDetails = responses.map(response => {
        return `
            <div class="response">
                <h3>Question ID: ${response.questionId}</h3>
                <p><strong>Selected Answer:</strong> ${response.selectedAnswer}</p>
                <p><strong>Correct Answer:</strong> ${response.correctAnswer}</p>
                <p><strong>Is Correct?</strong> ${response.isCorrect ? 'Yes' : 'No'}</p>
            </div>
        `;
    }).join('');


    resultsContainer.innerHTML += `
        <div class="responses">
            <h3>Question Responses:</h3>
            ${responseDetails}
        </div>
    `;
}


    fetchQuizData();
    submitBtn.addEventListener('click', submitQuiz);