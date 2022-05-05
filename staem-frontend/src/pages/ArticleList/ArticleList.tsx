import React from "react";

import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

import * as actions from "./ArticleList.actions";
import * as selectors from "./ArticleList.selectors";
import { useAppDispatch } from "../../store/store";

export default function ArticleList() {
  return (
    <div>
      <ArticleListFilterCard />
      <ArticleListResultCard />
    </div>
  );
}

function ArticleListFilterCard() {
  return <div>ArticleListFilterCard</div>;
}

function ArticleListResultCard() {
  const dispatch = useAppDispatch();

  const filter = useSelector(selectors.filter);
  const page = useSelector(selectors.page);
  const status = useSelector(selectors.status);

  React.useEffect(() => {
    dispatch(
      actions.findAll({
        ...filter,
        pageInfo: { index: page.pageNumber, size: page.pageSize },
      })
    );
  }, [dispatch, filter, page.pageNumber, page.pageSize]);

  const onPageChange = (pageNumber: number) => {
    dispatch(actions.page(pageNumber));
  };

  if (status === "error") {
  }

  if (status !== "success") {
  }

  return <ArticleListResults />;
}

function ArticleListResults() {
  const navigate = useNavigate();
  const result = useSelector(selectors.result);

  return <div>ArticleListResults</div>;
}
