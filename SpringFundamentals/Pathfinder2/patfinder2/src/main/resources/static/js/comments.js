const routeId = document.getElementById('routeId').value;
const commentForm = document.getElementById('commentForm');

const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').content;
const csrfValue = document.head.querySelector('[name=_csrf]').content;
const  commentContainer = document.getElementById('commentCtnr');

commentForm.addEventListener('submit', handleFormSubmission);

async function handleFormSubmission(event) {
    event.preventDefault();

    const messageVal = document.getElementById('message').value;

    fetch(`http://localhost:8080/api/${routeId}/comments`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accepts': 'application/json',
            [csrfHeaderName]: csrfValue
        },
        body: JSON.stringify({
            message: messageVal
        })
    }).then(res => res.json())
        .then(data => {
            document.getElementById('message').value = '';
            commentContainer.innerHTML += commentAsHtml(data);
        });
}

function commentAsHtml(comment){
    let commentHtml = '<div>\n';
    commentHtml += `<h4>${comment.authorName}</h4>\n`;
    commentHtml += `<p>${comment.message}</p>\n`;
    commentHtml += '</div>\n';

    return commentHtml;
}


fetch(`http://localhost:8080/api/${routeId}/comments`, {
    headers: {
        "Accept": "application/json"
    }
}).then(res=> res.json())
    .then(data => {
        for (let comment of data) {
            commentContainer.innerHTML += commentAsHtml(comment);
        }
    });

