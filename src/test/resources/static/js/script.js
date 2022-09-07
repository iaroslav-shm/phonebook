const AddButton = document.querySelector('.action__item--add');
const ResetButton = document.querySelector(".action__item--reset");

const NameInput = document.querySelector('.form__item--name');
const PhoneInput = document.querySelector('.form__item--phone');

const Table = document.querySelector('.table');

let deleteList = document.querySelectorAll('.action--delete');
let editList = document.querySelectorAll('.action--add');

const Modal = document.querySelector('.modal');
const ModalSave = document.querySelector('.modal__action--save');
const ModalCancel = document.querySelector('.modal__action--cancel');
const ModalName = document.querySelector('.modal__item--name');
const ModalPhone = document.querySelector('.modal__item--phone');

function listenerForDelete() {
  for(let i = 0; i < deleteList.length; i++) {
    deleteList[i].addEventListener('click', () => {
      deleteItem(BookArray[i].id);
    });
  }
}
listenerForDelete();

function listenerForEdit() {
  for(let i = 0; i < editList.length; i++) {
    editList[i].addEventListener('click', () => {
      ModalSetData(i, BookArray[i]);
    });
  }
}
listenerForEdit();

ModalSave.addEventListener('click', editItem);
ModalCancel.addEventListener('click', toggleModal);

let currentItem = null;

let modalState = false;

function reset() {
  NameInput.value = '';
  PhoneInput.value = '';
}

function toggleModal() {
  modalState = !modalState;
  if(modalState) {
    Modal.classList.remove('hidden');
  } else {
    Modal.classList.add('hidden');
    currentItem = null;
  }
}

function ModalSetData(index, item) {
  currentItem = item;
  ModalName.value = BookArray[index].name;
  ModalPhone.value = BookArray[index].phone;
  toggleModal();
}

async function getList() {
  try {
    const response = await fetch('http://localhost:8080/records');
    const data = await response.json()
    BookArray.splice(0, BookArray.length);
    data.forEach((item) => {
      BookArray.push({
        ...item
      })
    })
    console.log(data);
    updateTable();
  } catch(err) {
    console.log(err);
  }
}
getList();

async function addItem() {
  const body = JSON.stringify({
    name: NameInput.value,
    phone: PhoneInput.value
  })
  console.log(body);
  try {
    const response = await fetch('http://localhost:8080/records', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        mode: 'no-cors'
      },
      body: body
    })
    const data = await response.json();
    console.log(data);
    reset();
    getList();
  } catch(err) {
    console.log(err)
  }
}

async function editItem() {
  const url = 'http://localhost:8080/records/' + currentItem.id + '/';
  try {
    const response = await fetch(url, {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        name: ModalName.value,
        phone: ModalPhone.value
      })
    })
    console.log(response);
    toggleModal();
    getList();
  } catch(err) {
    console.log(err);
  }

}

async function deleteItem(id) {
  console.log(id)
  const url = 'http://localhost:8080/records/' + id + '/';
  try {
    const response = await fetch(url, {
      method: 'DELETE'
    })
    console.log(response);
    getList();
  } catch(err) {
    console.log(err);
  }
}

function normalizeDateTime(item) {
  const dateArr = item.split('T');
  const date = dateArr[0];
  const timeArr = dateArr[1].split('.');
  const time = timeArr[0];

  return date + ' ' + time;
}

function updateTable() {
  let Template = '';
  BookArray.forEach((item) => {
    Template +=
      `
        <li class="table__item">
            <p class="table__section">${item.name}</p>
            <p class="table__section">${item.phone}</p>
            <p class="table__section">${normalizeDateTime(item.lastModified)}</p>
            <span class="table-action__wrapper">
                <button class="table__action action--add">Edit</button>
            </span>
            <span class="table-action__wrapper">
                <button class="table__action action--delete">Delete</button>
            </span>
        </li>
      `
  })
  Table.innerHTML = Template;
  deleteList = document.querySelectorAll('.action--delete');
  editList = document.querySelectorAll('.action--add')
  listenerForDelete();
  listenerForEdit();
}

const BookArray = [];

ResetButton.addEventListener('click', reset);
AddButton.addEventListener('click', addItem);