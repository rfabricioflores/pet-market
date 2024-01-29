interface Ad {
  id: number;
  title: string;
  description: string;
  price: number;
  photos: AdPhoto[];
  editedAt: string;
  createdAt: string;
  owner: AdOwner | null | undefined;
}

interface AdOwner {
  id: number;
  firstname: string;
  lastname: string;
}

interface AdPhoto {
  id: number;
  url: string;
}

interface AdPagination {
  content: Ad[];
  pageable: {
    pageNumber: number;
    pageSize: number;
  };
  last: boolean;
  totalPages: number;
  totalElements: number;
  numberOfElements: number;
  first: boolean;
  empty: boolean;
}

export { Ad, AdPhoto, AdOwner, AdPagination };
